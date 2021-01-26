using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using Random = UnityEngine.Random;
using UnityEditor;

//FALTA: LEVEL MODIFICATION+ MODIFICAR MAPAS PARA TENER ALGO QUE HAGA DAÑO

public class BoardManager : MonoBehaviour
{
    [Serializable]
    public class Count
    {
        public int minimum;
        public int maximum;
        public Count(int min, int max)
        {
            minimum = min;
            maximum = max;
        }
    }
    public int columns = 29;
    public int rows = 29;
    public GameObject exit;
    public GameObject[] floorTiles;
    public GameObject[] items; //crystals,pills,pista
    public GameObject[] innerwallTiles;
    public GameObject[] outerWallTiles;
	public GameObject[] floorDamage;
    public GameObject[] signals;
    public GameObject player;
	public GameObject ship;
	private int level;


    private GameObject mainCamera;

    private Transform boardHolder;
    private List<Vector3> gridPositionsCleanerPart1 = new List<Vector3>(); 

    public void Start()
    {
        //mainCamera = GameObject.FindWithTag("MainCamera");
    }


    public void BoardSetup(int level)
    {
        GameObject board = new GameObject("Board");
        board.AddComponent(typeof(CompositeCollider2D));
        boardHolder = board.transform;
        boardHolder.GetComponent<Rigidbody2D>().bodyType = RigidbodyType2D.Static;
        GameObject playerinstance = new GameObject("Player");

		String map = 
            "#############################\r\n"
			+ "# I       I         I     I #\r\n"
			+ "# IIIIIII I  H  M  II IIIII #\r\n"
			+ "#     I  C              I   #\r\n"
			+ "# IIIII IIIII I I I IIIIIII #\r\n"
			+ "#       I     IDI I I   I   #\r\n"
			+ "# IIIIIII III I I III I III #\r\n"
			+ "# I  C  I I  DI I     I   I #\r\n"
			+ "# I IIIII I I I III IIIII I #\r\n"
			+ "#   I   I I I I I  D  I I   #\r\n"
			+ "#II I I IIIII III I I I ITII#\r\n"
			+ "# I   I C   U  C  I I   I   #\r\n"
			+ "# III III I I III I I III I #\r\n"
			+ "#   C I   I I I   I I     I #\r\n"
			+ "# IIIIIIIIIII IIIII IIIIIII #\r\n"
			+ "#  CI I   CD  I  C  H I     #\r\n"
			+ "# I I III IIIII IIIIIII IIII#\r\n"
			+ "# I I   C     I D I  C  I   #\r\n"
			+ "# I III III I IIIII I III I #\r\n"
			+ "# I I I   I I D I   I  C  I #\r\n"
			+ "#CI I IIIII III I IIIIIIIII #\r\n"
			+ "# I I   C   I I I I  C    I #\r\n"
			+ "# I IIIII I I III I IIIII I #\r\n"
			+ "# I D I C ID  I D I I     I #\r\n"
			+ "#IIII I III III IIIII III I #\r\n"
			+ "#  C  I I C IH  I I I I C   #\r\n"
			+ "# III I I I I III I III I I #\r\n"
			+ "# I   I I I I  EI       I I #\r\n"
			+ "#############################\r\n";  

        map = map.Replace("\r\n", "\n");
        String[] lines = map.Split('\n');
        String[] size = lines[0].Split(' ');
        int columns = 29;
        int rows = 29;

		
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {

				char chr = lines[y][x];
                switch (chr)
                {
                case '#': //Outer Wall UPDATE SEGUN LEVEL
					if (level==1){
	                    GameObject toInstantiate = outerWallTiles[0];
	                    GameObject instance = Instantiate(toInstantiate, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
						instance.transform.SetParent(boardHolder);
					}else if (level==2){
						GameObject toInstantiate = outerWallTiles[1];
						GameObject instance = Instantiate(toInstantiate, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
						instance.transform.SetParent(boardHolder);
					}else if (level==3){
						GameObject toInstantiate = outerWallTiles[2];
						GameObject instance = Instantiate(toInstantiate, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
						instance.transform.SetParent(boardHolder);
					}else if (level==4){
						GameObject toInstantiate = outerWallTiles[3];
						GameObject instance = Instantiate(toInstantiate, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
						instance.transform.SetParent(boardHolder);
					}

                    break;

                case 'E': //EXIT
					if (level==4){
						GameObject instanceship = Instantiate(ship, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
						instanceship.transform.SetParent(boardHolder);
						intantiateFloor(x, rows - y, rows, columns, level);

					}else{
						GameObject instanceExit = Instantiate(exit, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
						instanceExit.transform.SetParent(boardHolder);
						intantiateFloor(x, rows - y, rows, columns, level);
					}

                    break;

                case 'C': // cristal
					float rand3=Random.value;

					if (rand3<=0.60f){
                    GameObject instanceCrystal = Instantiate(items[0], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceCrystal.transform.SetParent(boardHolder);
					}
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;

                case 'P'://Player
                    //playerinstance = Instantiate(player, new Vector3(x, rows - y, 0f), Quaternion.identity);
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;

                case 'I': //IWall UPDATE SEGUN LEVEL
					if (level==1){
						GameObject toInstantiate3 = innerwallTiles[0];
						GameObject instanceIwall = Instantiate(toInstantiate3, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
						instanceIwall.transform.SetParent(boardHolder);
					}else if(level==2){
						GameObject toInstantiate3 = innerwallTiles[1];
						GameObject instanceIwall = Instantiate(toInstantiate3, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
						instanceIwall.transform.SetParent(boardHolder);
					}else if(level==3){
						GameObject toInstantiate3 = innerwallTiles[2];
						GameObject instanceIwall = Instantiate(toInstantiate3, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
						instanceIwall.transform.SetParent(boardHolder);
					}else if(level==4){
						GameObject toInstantiate3 = innerwallTiles[3];
						GameObject instanceIwall = Instantiate(toInstantiate3, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
						instanceIwall.transform.SetParent(boardHolder);
					}

                    break;


                case 'H': //pills
					float rand2=Random.value;


					if (rand2<=0.60f){
						GameObject instancePillsprob = Instantiate(items[1], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
						instancePillsprob.transform.SetParent(boardHolder);

					}
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;

                case 'M': //Pista
                    GameObject instancePista = Instantiate(items[2], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instancePista.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;

                case 'T': //SAbajo
                    GameObject instanceSAbajo = Instantiate(signals[0], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSAbajo.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;

                case 'Y': //SArriba
                    GameObject instanceSArriba = Instantiate(signals[1], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSArriba.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;

                case 'U': //SDerecha
                    GameObject instanceSDerecha = Instantiate(signals[2], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSDerecha.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;


                case 'O': //SIzquierda                     
                    GameObject instanceSIzquierda = Instantiate(signals[3], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSIzquierda.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;


                case 'S': //SDeath                     
                    GameObject instanceSDeath = Instantiate(signals[4], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSDeath.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns, level);
                    break;

                default://floor or damage floors

					float rand= Random.value;
					float difficult=0;
					if(level==1){
						difficult=0.15f;
					}else if(level==2){
						difficult=0.18f;
					}else if(level==3){
						difficult=0.2f;
					}else if(level==3){
						difficult=0.25f;
                    }
                    else if (level == 4)
                    {
                        difficult = 0.3f;
                    }

					if (rand<=difficult){
						int ytemp= rows-y;
						if (x==13 && ytemp==27){
							intantiateFloor(x, rows - y, rows, columns, level);

						}else{
							if (level==1){
								GameObject toInstantiateprob = floorDamage[0];
								GameObject instanceDprobwall = Instantiate(toInstantiateprob, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
								instanceDprobwall.transform.SetParent(boardHolder);
							}else if(level==2){
								GameObject toInstantiateprob = floorDamage[1];
								GameObject instanceDprobwall = Instantiate(toInstantiateprob, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
								instanceDprobwall.transform.SetParent(boardHolder);
							}else if(level==3){
								GameObject toInstantiateprob = floorDamage[2];
								GameObject instanceDprobwall = Instantiate(toInstantiateprob, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
								instanceDprobwall.transform.SetParent(boardHolder);
							}else if(level==4){
								GameObject toInstantiateprob = floorDamage[3];
								GameObject instanceDprobwall = Instantiate(toInstantiateprob, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
								instanceDprobwall.transform.SetParent(boardHolder);
							}
						}





					}else {
						intantiateFloor(x, rows - y, rows, columns, level);
					}


					break;


                }

            }


        }
		




    }

    public void intantiateFloor(int x, int y, int rows, int columns, int level)
    {
		if (level==1){
			GameObject floorToInstatiate = floorTiles[0];

			GameObject instanceFloor = Instantiate(floorToInstatiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
			instanceFloor.transform.SetParent(boardHolder);
		}else if(level==2){
			GameObject floorToInstatiate = floorTiles[1];

			GameObject instanceFloor = Instantiate(floorToInstatiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
			instanceFloor.transform.SetParent(boardHolder);
		}else if(level==3){
			GameObject floorToInstatiate = floorTiles[2];

			GameObject instanceFloor = Instantiate(floorToInstatiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
			instanceFloor.transform.SetParent(boardHolder);
		}else if(level==4){
			GameObject floorToInstatiate = floorTiles[Random.Range(3,4)];

			GameObject instanceFloor = Instantiate(floorToInstatiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
			instanceFloor.transform.SetParent(boardHolder);
		}



    }



    public void SetUpScene()
    {
        BoardSetup(level);

    }


}
