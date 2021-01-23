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
	public int columns = 30;
	public int rows = 30;
	public GameObject exit;
	public GameObject[] floorTiles;
	public GameObject[] items; //crystals,pills,pista
	public GameObject[] innerwallTiles;
	public GameObject[] outerWallTiles;
	public GameObject[] floorDamage;
	public GameObject[] signals;
	public GameObject player;

	private GameObject mainCamera;

	private Transform boardHolder;
	private List<Vector3> gridPositionsCleanerPart1 = new List<Vector3>(); 
	public void Start()
	{
		mainCamera = GameObject.FindWithTag("MainCamera");
	}


	void BoardSetup(string level)
	{
		GameObject board = new GameObject("Board");
		ObjectFactory.AddComponent<CompositeCollider2D>(board);
		boardHolder = board.transform;
		boardHolder.GetComponent<Rigidbody2D>().bodyType = RigidbodyType2D.Static;
		GameObject playerinstance = new GameObject("Player");

		String map = 
			 "#############################\r\n"
			+ "#CI          P    I       IH#\r\n" 
			+ "# I IIIIIIIIIMIIIII IIIII I #\r\n"
			+ "# I I   I   I I     I I I I #\r\n"
			+ "# IIIII I III I IIIII I I I #\r\n"
			+ "#   I I I   I   I       I   #\r\n"
			+ "#II I I I I III I IIIII IIII#\r\n"
			+ "#  O  I I I I I   I   I     #\r\n"
			+ "# I III III I I III I IIIIII#\r\n"
			+ "# I     I     I   ICI I I   #\r\n"
			+ "# IIIII I IIIIIII III I III #\r\n"
			+ "#     I          O          #\r\n"
			+ "# III IIIII IIIII IIIIIII II#\r\n"
			+ "#   I   I   I  CI   I   I   #\r\n"  
			+ "#II III III I IIIII I I III #\r\n"
			+ "#   I   I IHI I ICI I I   IC#\r\n"
			+ "# I IIIII III I I III III II#\r\n"
			+ "# I I   I   I I I I     I   #\r\n"
			+ "# III I I III I I I III III #\r\n"
			+ "# I   I  T  I I     I     I #\r\n"
			+ "# I III I I I I IIIIIII III #\r\n"
			+ "#     I I I   I   I   I IC  #\r\n"
			+ "# IIIIIII III III I I IIIIII#\r\n"
			+ "# I         I   I I I IC    #\r\n" 
			+ "# IIIIIIIII III I I I IIIII #\r\n"
			+ "#   ICI     I     I I     I #\r\n"
			+ "#II I I IIIIIIIII I IIIII I #\r\n"
			+ "#     I        EI       I   #\r\n"
			+ "#############################\r\n";

		map = map.Replace("\r\n", "\n");
		String[] lines = map.Split('\n');
		String[] size = lines[0].Split(' ');

		for (int y = 0; y < 29; y++)
		{
			for (int x = 0; x < 29; x++)
			{
				char chr = lines[y + 1][x];
				switch (chr)
				{
				case '#': //Outer Wall UPDATE SEGUN LEVEL
					GameObject toInstantiate = outerWallTiles[Random.Range(0, outerWallTiles.Length)];
					GameObject instance = Instantiate(toInstantiate, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
					instance.transform.SetParent(boardHolder);
					break;

				case 'E': //EXIT
					GameObject instanceExit = Instantiate(exit, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instanceExit.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;

				case 'C': // cristal
					GameObject instanceCrystal = Instantiate(items[0], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instanceCrystal.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;

				case 'P'://Player
					instancePlayer = Instantiate(player, new Vector3(x, rows - y, 0f), Quaternion.identity);
					intantiateFloor(x, rows - y, rows, columns);
					break;

				case 'I': //IWall UPDATE SEGUN LEVEL
					GameObject toInstantiate = innerWallTiles[Random.Range(0, innerWalls.Length)];
					GameObject instanceIwall = Instantiate(toInstantiate, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
					instanceIwall.transform.SetParent(boardHolder);
					break;

				case 'D'://FloorDamage
					GameObject toInstantiate2 = floorDamage[Random.Range(0, floorDamage.Length)];
					GameObject instanceDwall = Instantiate(toInstantiate2, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
					instanceIwall.transform.SetParent(boardHolder);
					break;

				case 'H': //pills
					GameObject instancePills = Instantiate(items[1], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instancePills.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;

				case 'M': //Pista
					GameObject instancePista = Instantiate(items[2], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instancePista.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;

				case 'T': //SAbajo
					GameObject instanceSAbajo = Instantiate(signals[0], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instanceSAbajo.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;

				case 'Y': //SArriba
					GameObject instanceSArriba = Instantiate(signals[1], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instanceSArriba.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;

				case 'U': //SDerecha
					GameObject instanceSDerecha = Instantiate(signals[2], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instanceSDerecha.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;


				case 'S': //SIzquierda                     
					GameObject instanceSIzquierda = Instantiate(signals[3], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instanceSIzquierda.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;


				case 'O': //SDeath                     
					GameObject instanceSDeath = Instantiate(signals[4], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
					instanceSDeath.transform.SetParent(boardHolder);
					intantiateFloor(x, rows - y, rows, columns);
					break;

				default:
					intantiateFloor(x, rows - y, rows, columns);
					break;

				}
			}


		}
		//Añadimos la camara de seguimiento del personage.

		CameraFollow camerascript = mainCamera.GetComponent<CameraFollow>();
		camerascript.following = playerinstance;
		//GameObject camerainstance =Instantiate(mainCamera, new Vector3(playerinstance.transform.position.x, playerinstance.transform.position.y, -10f), Quaternion.identity);


	}

	public void intantiateFloor(int x, int y, int rows, int columns)
	{
		GameObject floorToInstatiate = floorTiles[Random.Range(0, floorTiles.Length)];
		if (x == 1 || x == columns - 2 || y == 2 || y == rows - 1)
			floorToInstatiate = outerWall[Random.Range(0, outerWallTiles.Length)];
		
		GameObject instanceFloor = Instantiate(floorToInstatiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
		instanceFloor.transform.SetParent(boardHolder);
	}

//	void LayoutObjectAtRandom(GameObject[] tileArray, int minimum, int maximum)
//	{
//		//Choose a random number of objects to instantiate within the minimum and maximum limits
//		int objectCount = Random.Range(minimum, maximum + 1);
//
//		//Instantiate objects until the randomly chosen limit objectCount is reached
//		for (int i = 0; i < objectCount; i++)
//		{
//			//Choose a position for randomPosition by getting a random position from our list of available Vector3s stored in gridPosition
//			Vector3 randomPosition = RandomPosition();
//
//			//Choose a random tile from tileArray and assign it to tileChoice
//			GameObject tileChoice = tileArray[Random.Range(0, tileArray.Length)];
//
//			//Instantiate tileChoice at the position returned by RandomPosition with no change in rotation
//			Instantiate(tileChoice, randomPosition, Quaternion.identity);
//		}
//	}

//	Vector3 RandomPosition()
//	{
//		int randomIndex = Random.Range(0, gridPositionsCleanerPart1.Count);
//
//
//		Vector3 randomPosition = gridPositionsCleanerPart1[randomIndex];
//
//
//		gridPositionsCleanerPart1.RemoveAt(randomIndex);
//
//		return randomPosition;
//	}

	public void SetUpScene()
	{
		BoardSetup();
		//LayoutObjectAtRandom(cleanersSprite, cleanerCount.minimum, cleanerCount.maximum);

		BoardSetupFromString(level);
	}


}
